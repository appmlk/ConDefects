N = int(input())
S = input()
C = list(map(int,input().split()))
OI = [0 for i in range(N)]
IO = [0 for i in range(N)]
if S[0] == "0":
    OI[0] = 0
    IO[0] = C[0]
else:
    IO[0] = 0
    OI[0] = C[0]



for i in range(1,N):
    if S[i] == "0" and i % 2 == 0:
        IO[i] = IO[i-1] + C[i]
        OI[i] = OI[i-1]
    elif S[i] == "0" and i % 2 == 1:
        OI[i] = OI[i - 1] + C[i]
        IO[i] = IO[i - 1]
    if S[i] == "1" and i % 2 == 0:
        OI[i] = OI[i-1] + C[i]
        IO[i] = IO[i-1]
    elif S[i] == "1" and i % 2 == 1:
        IO[i] = IO[i - 1] + C[i]
        OI[i] = OI[i - 1]

ans = set()
for i in range(N-1):
    ans.add((OI[-1]-OI[i])+(IO[i]))
    ans.add(IO[-1]-IO[i]+(OI[i]))

print(min(ans))





