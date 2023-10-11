moon = list(map(int, input().split()))

N = moon[0]
M = moon[1]
P = moon[2]

count = 0

while M <= N:
    count = count + 1
    M = M + P

print(count)