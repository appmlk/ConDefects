from functools import cmp_to_key
def ask(i,j,k):
  print("?",i,j,k)
  return input() == "Yes"
class box():
    def __init__(self,i):
        self.i = i
    def __lt__(self,other):
        return ask(other.i,now,self.i)
N = int(input())
for i in range(3702):
  ask(1,1,1)
now = 1
for i in range(2,N+1):
  if ask(now,now,i):
    now = i
li = [box(i+1) for i in range(N)]
li.sort()
X = [-1]*N
for i in range(N):
    X[li[i].i-1] = i+1
print("!",*X)
