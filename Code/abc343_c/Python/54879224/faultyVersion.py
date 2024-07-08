def isok(x):
    x = str(x)
    for i in range(len(x)//2):
        if x[i] != x[-i-1]:
            return False
    return True

n = int(input())
ans = 0
for i in range(n):
    if i**3 <= n and isok(i**3):
        ans = i**3
    elif i**3 > n:
        break
print(ans)