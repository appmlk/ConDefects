n = int(input())
x = 0
y = 0
for i in range(n):
    a, b= map(int,input().split())
    x += a
    y += b

if x > y: print("Takahashi")
elif x < y: print("Aoki")
else: print("Drow")

