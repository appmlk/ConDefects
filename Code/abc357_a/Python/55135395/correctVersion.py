N,M=map(int,input().split())
H = list(map(int,input().split()))
cnt=0
for i in range(len(H)):
    if M-H[i] < 0:
        break
    M = M - H[i]
    cnt += 1
print(cnt)