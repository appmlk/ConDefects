N=int(input())
S=input()
left=0 if S[0]=="o" else -1
max_L=-1
for right in range(1,N):
  if S[right-1]=="-" and S[right]=="o":
    left = right
  elif S[right-1]=="o" and S[right]=="-":
    #print(left, right, right-left)
    if 0<=left-1 and S[left-1]=="-" or right+1<N and S[right+1]=="-":
      max_L = max(max_L, right-left)
if S[-1]=="o" and 0<=left-1 and S[left-1]=="-":
  #print(left, N, N-left)
  max_L = max(max_L, N-left)
print(max_L)