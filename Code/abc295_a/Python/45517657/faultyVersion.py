n = int(input())
s = list(input())
ary = set(s)
keys = ["and", "not", "that", "the", "you"]
for key in keys:
    if key in ary:
        print("Yes")
        break
else:
    print("No")