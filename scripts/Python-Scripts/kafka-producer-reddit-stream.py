from json import dumps
import json
import praw
from kafka import KafkaProducer
import time
from datetime import datetime

producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                         value_serializer=lambda x:
                         dumps(x).encode('utf-8'))

file = open("reddit-data.json", "a")

secretKey = "Zj4X5Pv1aC-gFWYlk6CaJWSYyTc"
publicKey = "YQA1Qb-lfTEnzw"
userName = "bigDataProject"

reddit = praw.Reddit(client_id=publicKey, client_secret=secretKey, user_agent=userName)


for submission in reddit.subreddit('all').stream.submissions():
    li = []
    li2 = dumps([])
    try:
        li = list(submission.comments)
        li2 = dumps(li)
    except:
        li2 = dumps([])
    data = {'author': str(submission.author),
            'name': submission.title,
            'id': submission.id,
            'time-created': time.strftime("%D %H:%M", time.localtime(int(submission.created_utc))),
            'locked': submission.locked,
            'comments': li2,
            'is_original_content': submission.is_original_content,
            'num_of_comments': submission.num_comments,
            'score': submission.score,
            'upvote_ratio': submission.upvote_ratio
           }
    print(data)
    json.dump(data, file)
    file.write('\n')
    producer.send('final-lab-topic', value=data)
    time.sleep(1.5)



