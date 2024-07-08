N = int(input())
S = input()
C = list(map(int, input().split()))

zeroichi = ['1' if i%2 == 1 else '0' for i in range(10**6) ]
ichizero = ['0' if i%2 == 1 else '1' for i in range(10**6) ]
ZL = [0]*(N+1)
Crev = C[::-1]
#0スタートパターン左
for i in range(N):
    tmp = 0
    if S[i] != zeroichi[i]:
        tmp = C[i]
    ZL[i+1] = ZL[i]+tmp
#0スタートパターン右
ZR = [0]*(N+1)
Srev = S[::-1]
for i in range(N):
    tmp = 0
    if Srev[i] != zeroichi[i]:
        tmp = Crev[i]
    ZR[i+1] = ZR[i]+tmp

#1スタートパターン左
OL = [0]*(N+1)
for i in range(N):
    tmp = 0
    if S[i] != ichizero[i]:
        tmp = C[i]
    OL[i+1] = OL[i]+tmp
#0スタートパターン右
OR = [0]*(N+1)
for i in range(N):
    tmp = 0
    if Srev[i] != ichizero[i]:
        tmp = Crev[i]
    OR[i+1] = OR[i]+tmp

if N % 2 == 0: #偶数なら、０スタ０スタ、１スタ１スタで比べる
    ANS = 10**50
    for i in range(N):
        ANS = min((OL[i]+OR[N-i]),ZL[i]+ZR[N-i],ANS)
    print(ANS)
else:
    ANS = 10**50
    for i in range(N):
        ANS = min((OL[i]+ZR[N-i]),ZL[i]+OR[N-i],ANS)
    print(ANS)





