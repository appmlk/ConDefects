A,B,D=map(int,input().split())
ans=[A]
while A+D<=B:
  ans.append(A+D)
  A+=D
print(*ans)