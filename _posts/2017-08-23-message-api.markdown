---
layout: post
title:  "Message API"
date:   2017-08-23 15:00:09 -0200
permalink: 
---

## URL

{{ site.server_url }}

## Send a message
`
POST /api/users/:userId/messages
`

### Parameter

- :userId: sender's unique identifier

### Request
1. Send a plain message 

	{% highlight json %}
	{
		"message": {
			"text" : "请问你还招聘工人吗"
		},
		"recipients" : [
			{"id" : 2},
			{"id" : 3}
		]
	}
	{% endhighlight %}

2. Send a message under a task

	{% highlight json %}
	{
		"task": {
			"id" : 63
		},
		"message": {
			"text" : "请问你还招聘工人吗"
		},
		"recipients" : [
			{"id" : 2},
			{"id" : 3}
		]
	}
	{% endhighlight %}

## Reply a message
`
PUT /api/users/:userId/messages/:messageId
`

### Parameter

- :userId: sender's unique identifier
- :id: task unique identifier

### Request
{% highlight json %}
{
	"message": {
		"text" : "你好，我很感兴趣，请问你找到合适的人选了吗？"
	},
	"recipients" : [
		{"id" : 1}
	]
}
{% endhighlight %}

## Get all sent messages by user ##
`
GET /api/users/:userId/messages/sent
`

### Parameter

- :userId: sender's unique identifier

### Response ###
{% highlight json %}
[
    {
        "parent": null,
        "id": 27,
        "text": "请问你还招聘工人吗",
        "messageLines": [],
        "childMessages": [],
        "task": null,
        "sender": {
            "id": 1,
            "avatar": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/8/005/01e/0bd/31f0f9f.jpg",
            "phoneNumber": "647-123-1234",
            "nickName": "Andy",
            "sentMessages": [],
            "threadLines": [],
            "userName": "andyf",
            "receivedMessages": [],
            "password": "123",
            "tasks": [],
            "email": "andyinbox3@gmail.com",
            "createdTime": 1502206250000
        },
        "createdTime": 1503159535000,
        "thread": {
            "id": 25,
            "threadLines": [],
            "messages": []
        }
    }, 
	...
}
{% endhighlight %}

## Get all received messages by user ##
`
GET /api/users/:userId/messages/sent
`

### Parameter

- :userId: sender's unique identifier

### Response ###
{% highlight json %}
[
    {
        "parent": {
            "parent": null,
            "id": 76,
            "createdTime": 1503207647000,
            "sender": {
                "id": 1,
                "createdTime": 1502206250000,
                "password": "123",
                "userName": "andyf",
                "tasks": [],
                "email": "andyinbox3@gmail.com",
                "phoneNumber": "647-123-1234",
                "nickName": "Andy",
                "avatar": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/8/005/01e/0bd/31f0f9f.jpg",
                "threadLines": [],
                "sentMessages": [],
                "receivedMessages": []
            },
            "messageLines": [],
            "thread": {
                "id": 74,
                "messages": [],
                "threadLines": []
            },
            "text": "请问你还招聘工人吗",
            "task": {
                "address": "123 queen street",
                "id": 63,
                "content": "本人打算装修地下室，面积300尺，预算5万左右，希望寻找一名有经验的装修师傅",
                "startTime": "2017-09-01 12:00:00",
                "endTime": null,
                "author": {
                    "id": 1,
                    "createdTime": 1502206250000,
                    "password": "123",
                    "userName": "andyf",
                    "tasks": [],
                    "email": "andyinbox3@gmail.com",
                    "phoneNumber": "647-123-1234",
                    "nickName": "Andy",
                    "avatar": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/8/005/01e/0bd/31f0f9f.jpg",
                    "threadLines": [],
                    "sentMessages": [],
                    "receivedMessages": []
                },
                "category": {
                    "name": "接送",
                    "parent": null,
                    "id": 1,
                    "tasks": [],
                    "childCategories": []
                },
                "createdTime": "2017-08-10 12:13:01",
                "messages": [],
                "price": 50000,
                "title": "找装修师傅"
            },
            "childMessages": []
        },
        "id": 118,
        "createdTime": 1503273807000,
        "sender": {
            "id": 2,
            "createdTime": 1500132650000,
            "password": "123",
            "userName": "rachaelh",
            "tasks": [],
            "email": "andyinbox4@gmail.com",
            "phoneNumber": "207-123-1234",
            "nickName": "Rachael",
            "avatar": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAJSAAAAJDI1ZmE4YzgxLWEzZGUtNDFhZi04Yzg0LTMwNzBkYjgzY2ZiNQ.jpg",
            "threadLines": [],
            "sentMessages": [],
            "receivedMessages": []
        },
        "messageLines": [],
        "thread": {
            "id": 74,
            "messages": [],
            "threadLines": []
        },
        "text": "你好，我很感兴趣，请问你找到合适的人选了吗？",
        "task": {
            "address": "123 queen street",
            "id": 63,
            "content": "本人打算装修地下室，面积300尺，预算5万左右，希望寻找一名有经验的装修师傅",
            "startTime": "2017-09-01 12:00:00",
            "endTime": null,
            "author": {
                "id": 1,
                "createdTime": 1502206250000,
                "password": "123",
                "userName": "andyf",
                "tasks": [],
                "email": "andyinbox3@gmail.com",
                "phoneNumber": "647-123-1234",
                "nickName": "Andy",
                "avatar": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/8/005/01e/0bd/31f0f9f.jpg",
                "threadLines": [],
                "sentMessages": [],
                "receivedMessages": []
            },
            "category": {
                "name": "接送",
                "parent": null,
                "id": 1,
                "tasks": [],
                "childCategories": []
            },
            "createdTime": "2017-08-10 12:13:01",
            "messages": [],
            "price": 50000,
            "title": "找装修师傅"
        },
        "childMessages": []
    },
	...
}
{% endhighlight %}

## Get a message by id
`
GET /api/messages/:id
`

### Parameter

- :id: message unique identifier

### Response

{% highlight json %}

{
    "parent": null,
    "id": 76,
    "text": "请问你还招聘工人吗",
    "task": {
        "address": "123 queen street",
        "id": 63,
        "content": "本人打算装修地下室，面积300尺，预算5万左右，希望寻找一名有经验的装修师傅",
        "endTime": null,
        "startTime": "2017-09-01 12:00:00",
        "messages": [],
        "title": "找装修师傅",
        "category": {
            "name": "接送",
            "parent": null,
            "id": 1,
            "childCategories": [],
            "tasks": []
        },
        "createdTime": "2017-08-10 12:13:01",
        "author": {
            "id": 1,
            "receivedMessages": [],
            "userName": "andyf",
            "password": "123",
            "sentMessages": [],
            "nickName": "Andy",
            "avatar": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/8/005/01e/0bd/31f0f9f.jpg",
            "threadLines": [],
            "phoneNumber": "647-123-1234",
            "createdTime": 1502206250000,
            "tasks": [],
            "email": "andyinbox3@gmail.com"
        },
        "price": 50000
    },
    "sender": {
        "id": 1,
        "receivedMessages": [],
        "userName": "andyf",
        "password": "123",
        "sentMessages": [],
        "nickName": "Andy",
        "avatar": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/8/005/01e/0bd/31f0f9f.jpg",
        "threadLines": [],
        "phoneNumber": "647-123-1234",
        "createdTime": 1502206250000,
        "tasks": [],
        "email": "andyinbox3@gmail.com"
    },
    "createdTime": 1503207647000,
    "thread": {
        "id": 74,
        "messages": [],
        "threadLines": []
    },
    "messageLines": [],
    "childMessages": []
}

{% endhighlight %}
