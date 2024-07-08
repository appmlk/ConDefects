B=int(input())
for i in range(1,20):
    if i**i==B:
        print(i)
        break
    if i**i>=10**18:
        print(-1)
        break