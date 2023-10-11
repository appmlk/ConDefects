import itertools

S = list(input()) 
T = list(input())

s = [[key , len(list(group))] for key , group in itertools.groupby(S)] 
t = [[key , len(list(group))] for key , group in itertools.groupby(T)]

if len(s) != len(s) :
  print('No')
  exit()
  
for i in range(len(s)) :
  if s[i][0] != t[i][0] :
    print('No')
    exit()
  if s[i][1] > t[i][1] :
    print('No')
    exit()
  if s[i][1] < t[i][1] :
    if s[i][1] < 2 :
      print('No')
      exit()
    
print('Yes')