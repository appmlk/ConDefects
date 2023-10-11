N = int(input())
A = list(map(int,input().split()))

for i in range(N):
    if i not in A:
        print(i)
        break