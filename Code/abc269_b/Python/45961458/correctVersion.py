n = 10
s = [input()for i in range(n)]



a,b,c,d = 100, 0, 100, 0
for i in range(n):
    for j in range(n):
      if s[i][j]=='#':
        if a>i : a=i
        if b<i : b=i
        if c>j : c=j
        if d<j : d=j
print(a+1,b+1)
print(c+1,d+1)