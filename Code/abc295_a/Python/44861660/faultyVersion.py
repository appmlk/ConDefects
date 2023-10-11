N = int(input())
word_list = map(str,input().split())
keywords = ["and", "not", "that", "the", "you"]

found = False
for keyword in keywords:
    if keyword in word_list:
        found = True
        break

if found:
    print("Yes")
else:
    print("No")
