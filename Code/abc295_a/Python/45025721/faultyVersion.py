S = ["and", "not", "that", "the", "you"]
N  = int(input())
W = list(input().split())
for w in W:
    if w in S:
        exit(print("Yes"))
print("NO")