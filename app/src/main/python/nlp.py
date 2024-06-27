from textblob import TextBlob

def process_text_and_get_sentiment(text):
    #Call the sentiment analysis function
    result = analyze_and_interpret_sentiment(text)
    return result

def analyze_and_interpret_sentiment(text):
    def analyze_sentiment(text):
        blob = TextBlob(text)
        sentiment_score = blob.sentiment.polarity
        return sentiment_score

    # Analyze the sentiment
    sentiment_score = analyze_sentiment(text)

    # Interpret the sentiment
    if sentiment_score > 0:
        return "happy"
    elif sentiment_score < 0:
        return "sad"
    else:
        return "neutral"