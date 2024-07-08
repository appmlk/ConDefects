class Node:
  def __init__(self, value=""):
    self.nex = None
    self.pre = None
    self.value = value


#N = 5
#S = "LRRLR"
N = int(input())
S = input()

nil = Node()
nil.nex = nil
nil.pre = nil
recent_node = Node(0)
recent_node.nex = nil
recent_node.pre = nil

for i in range(1, N+1):
  new_node = Node(i)
  if S[i-1] == "L":
    A = recent_node.pre
    A.nex = new_node
    new_node.nex = recent_node
    new_node.pre = A
    recent_node.pre = new_node
  else: 
    B = recent_node.nex
    B.pre = new_node
    new_node.nex = B
    new_node.pre = recent_node
    recent_node.nex = new_node

  recent_node = new_node
  #print(recent_node.value)

after_recent = list()
n = recent_node
while(n != nil):
  #print(n.value)
  after_recent.append(n.value)
  n = n.nex 

before_recent = list()
n = recent_node
while(n != nil):
  #print(n.value)
  before_recent.append(n.value)
  n = n.pre

before_recent.reverse()
ans = before_recent[:-1] + after_recent
print(*ans)



