def row(x, n):
    return (x-1)//n
def col(x, n):
    return (x-1)%n 
def diag1(x,n, lst1):
    return (x-1) in lst1
def diag2(x,n, lst2):
    return (x-1) in lst2

n, t = [int(num) for num in input().split()]
lst = [int(num) for num in input().split()]
lst1 = [num  for num in range(0, n*n, n+1)]
lst2 = [num  for num in range(n-1, n*(n-1)+1, n-1)]
rows = [0 for i in range(n)]
cols = [0 for i in range(n)]
d1 = 0
d2 = 0
bingo=-1
for i in range(len(lst)): 
    r = row(lst[i], n)
    c = col(lst[i], n)
    rows[r]+=1 
    cols[c]+=1 
    if r==c:
        d1+=1 
    if r+c==(n-1):
        d2+=1 
    if rows[r]==n or cols[c]==n or d1==n or d2==n:
        bingo = i+1
        break  
print(bingo)