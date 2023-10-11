n=int(input())

if n%5<3:
    ans=n-(n%5)
else:
    ans=n+5-(n%5)
print(ans)