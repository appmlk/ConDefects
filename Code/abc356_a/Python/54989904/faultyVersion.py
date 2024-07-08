N,L,R = map(int,input().split())
L-=1
A=list(range(1,N+1))

A[L:R] = reversed(A[L:R])

result = ' '.join(map(str, A))

print(f"A' = ({result})")