N,K = map(int,input().split())
A = [0] * N
S = []

def request(i):
    if i <= K:
        X = [(k+i)%(K+1)+1 for k in range(K)]
    else:
        X = [k+1 for k in range(K-1)] + [i+1]
    print("?",*X)
    s = int(input())
    S.append(s)

for i in range(N):
    request(i)

T = sum(S[:K+1]) % 2
for i in range(K+1):
    A[(i-1)%(K+1)] = (T-S[i]) % 2

for i in range(K+1,N):
    A[i] = (S[0]-A[K-1]-S[i]) % 2

print("!",*A)