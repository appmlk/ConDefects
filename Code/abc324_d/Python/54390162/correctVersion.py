# D - Square Permutation
import copy
from collections import Counter
N, dic = None, None

def main():
  global N, dic
  N = int(input())
  S = list(input())
  
  dic = Counter(S)
  upper = get_upper_limit(S)
  i = 0
  ans = 0
  
  while i**2 <= upper:
    if can_sort(i**2):
      ans += 1
      #print(i**2, ans)
    
    i += 1
  
  print(ans)
  
  
def get_upper_limit(S):
  arr = copy.deepcopy(S)
  arr.sort(reverse=True)
  
  s = ''.join(arr)
  return int(s)


def can_sort(n):
  s = str(n).zfill(N)
  c = Counter(list(s))
  
  if len(c) == len(dic):
    for k, v in c.items():
      matches = False
      
      if k in dic.keys():
        if v == dic[k]:
          matches = True
      
      if not matches:
        return
  else:
    return 
  
  return True
  

if __name__ == '__main__':
  main()