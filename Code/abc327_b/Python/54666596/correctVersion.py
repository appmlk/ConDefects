B = int(input())
i = 1
while pow(i,i) < B:
    i += 1
if pow(i,i) == B:
    print(i)
else:
    print(-1)