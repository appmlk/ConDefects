I=input;R=lambda:[*map(int,I().split())]
def f(a,sign=1,chk=False):
  for i in range(1,n,2):
    if a[i]*sign>a[i+1]*sign:
      if i!=1 and a[i]*sign<a[i-2]*sign or a[i+1]*sign<a[i-1]*sign:
        return 0
      if chk and (s[a[i]]!='' or s[a[i+1]]!=''):return 0
      s[a[i]]='(';s[a[i+1]]=')'
  return 1
n=int(I())*2+1;p=[0]+R();q=[0]+R();s=['']*n
x,y=f(p),f(q,-1,True);s=s[1:]
print([-1,''.join(s)][x and y and all(c!='' for c in s)])