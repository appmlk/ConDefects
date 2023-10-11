n,m = map(int,input().split())
a = [input() for _ in range(n)]

for i in range(n-9+1):
    for j in range(m-9+1):
        if (all(row[j:j+4] == "###." for row in a[i:i+3]) and 
            a[i+3][j:j+4] == "...." and a[i+5][j+5:j+9] =="...." and
            all(row[j+5:j+9] == ".###" for row in a[i+6:i+9])):
            print(i+1,j+1)