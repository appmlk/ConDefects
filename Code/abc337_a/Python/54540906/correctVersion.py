N = int(input())

X = 0
Y = 0
for i in range(N):
    x, y = map(int,input().split())
    X += x
    Y += y

if X > Y:
    print("Takahashi")
elif X == Y:
    print("Draw")
else:
    print("Aoki")

