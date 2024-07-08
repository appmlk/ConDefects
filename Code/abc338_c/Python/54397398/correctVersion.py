N = int(input())
material = list(map(int, input().split()))
A = list(map(int, input().split()))
B = list(map(int, input().split()))

def canmake(K): #K人分の料理を作れるか判定
  judge = False
  for i in range(K):
    local_judge = True
    for j in range(N):
      if A[j]*i + B[j]*(K-i) > material[j]:
        local_judge = False
    if local_judge == True:
      judge = True
  return judge
  
L, R = 0, 10**7
while R-L > 1:
  C = (R+L)//2
  if canmake(C):
    L = C
  else:
    R = C
    
print(L)