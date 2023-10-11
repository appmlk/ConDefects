num = sorted(list(map(int, input().split())))

n = num[1] - (num[2]-num[0])
if n < 0:
    print(-1)
else:
    print(num[2])
