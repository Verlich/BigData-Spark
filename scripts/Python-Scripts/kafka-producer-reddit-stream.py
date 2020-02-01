from json import dumps
import praw
from kafka import KafkaProducer
import time

data ={'Test sending data': 'value sent'}
producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                         value_serializer=lambda x:
                         dumps(x).encode('utf-8'))

producer.send('final-lab-topic', value=data)

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
            'name': submission.name,
            'id': submission.id,
            'time-created': submission.created_utc,
            'locked': submission.locked,
            'comments': li2,
            'is_original_content': submission.is_original_content,
            'num_of_comments': submission.num_comments,
            'score': submission.score,
            'upvote_ratio': submission.upvote_ratio
           }
    print(data)
    producer.send('final-lab-topic', value=data)
    time.sleep(1)



