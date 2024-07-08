N,M=input().split()
S=tuple(input())
T=tuple(input())

if S==T[:int(N)]:
  if S==T[int(M)-int(N):]:
    print(0)
  else:
    print(1)
else:
  if S==T[int(M)-int(N):]:
    print(2)
  else:
    print(3)