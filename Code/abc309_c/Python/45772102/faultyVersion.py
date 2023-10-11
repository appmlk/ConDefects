N, K = map(int, input().split())
AB = [tuple(map(int, input().split())) for _ in range(N)]

AB.sort()
medicines = 0
for i in range(N):
    medicines += AB[i][1]

for i in range(N):
    if medicines <= K:
        if i == 0:
            print(1)
            exit(0)
        else:
            print(AB[i - 1][0] + 1)
            exit(0)
    medicines -= AB[i][1]