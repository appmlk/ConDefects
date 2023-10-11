M = int(input())

D = list(map(int, input().split()))
X = (sum(D)+1)//2
S = 0

for i in range(M):
    S = S + D[i]
    if S >= X:
        exit(print(i+1, X-S+D[i]))
        exit(print(str(i+1) + ' ' + str(X - S + D[i])))