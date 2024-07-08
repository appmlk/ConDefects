n = int(input())
A = list(map(int, input().split()))


x = 0
s = 0

for i in A:
    s += i
    if s < 0:
        x += (-s)

        s=0

print(sum(A) + x)

