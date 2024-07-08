def neq(x):
  n = len(x)
  ans = 0
  if n==1:
    return int(x)
  for i in range(n):
    if i==0:
      ans += (int(x[i])-1)*(9**(n-1))+neq('9'*(n-1))
    elif i==n-1:
      if int(x[i-1])<=int(x[i]):
        return ans+int(x[i])
      else:
        return ans+int(x[i])+1
    elif int(x[i-1])<int(x[i]):
      ans += (int(x[i])-1)*(9**(n-1-i))
    elif x[i-1]==x[i]:
      ans += int(x[i])*(9**(n-1-i))
      return ans
    else:
      ans += (int(x[i]))*(9**(n-1-i))


T = int(input())
for _ in range(T):
  k = int(input())
  M = k*10
  s,l = k,M
  while True:
    m = (s+l)//2
    x = neq(str(m))
    if x<k:
      s=m
    elif x>k:
      l=m
    else:
      m=str(m)
      while True:
        for j in range(0,len(m)-1):
          if m[j]==m[j+1]:
            if j+2<len(m)-1:
              m = str(int(m[:j+2])-1)+'9'*(len(m)-j-2)
            else:
              m = str(int(m)-1)
            break
        else:
          print(int(m))
          break
      break
        