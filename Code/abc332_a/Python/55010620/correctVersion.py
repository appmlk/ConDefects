N,S,K = map(int,input().split())
x = 0
for i in range (N):
    P,Q = map(int,input().split())
    x += P*Q
if S > x:
    x += K
print(x)
