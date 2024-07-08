N = int(input())
a = list(input())
a_cost = list(map(int,input().split())) + [0]
zo = [0 for _ in range(N)]
oz = [0 for _ in range(N)]
for an in range(N):
    if a[an] == str(0 + an % 2):
        zo[an] = zo[an -1]
        oz[an] = oz[an -1] + a_cost[an]
    elif a[an] == str((1 + an) % 2):
        zo[an] = zo[an -1] + a_cost[an]
        oz[an] = oz[an -1]
min_m = 10 ** 24
x = zo[-1]
y = oz[-1]
for n in range(N):
    min_m = min(min_m, zo[n] -oz[n]+y, oz[n]-zo[n] +x)
print(min_m)
#print(zo,oz)