
def check(b,c):
 #start at a and end at bth day, check if everyone is available
  for i in range(N):
    for j in range(b,c+1):
      if(a[i][j] == 'x'):
        return False
  return True
  
a = []
temp  = []
N,D = map(int,input().split()) #N = 3, D = 5

for i in range(N):
  s = input()
  a.append(s)
for i in range(N):
  print(a[i])
ans = 0
for i in range(D):
  for j in range(i,D):
    #start at i and end at jth day
    if(check(i,j) == True):
      ans = max(ans,j-i+1)
    
print(ans)

# x = int((1 + D)*D/2)
# for i in range(N):
#   i = input("ox")
#   a.append(i)
# for i in range(x):
#   print("hello")
  