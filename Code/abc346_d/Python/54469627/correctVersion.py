n = int(input())
s = input()
c = list(map(int, input().split()))
zo = [0]
oz = [0]

for i in range(n):
    if i % 2:
        if s[i] == '1':
            oz.append(oz[-1]+c[i])
            zo.append(zo[-1])
        else:
            zo.append(zo[-1]+c[i])
            oz.append(oz[-1])
    else:
        if s[i] == '1':
            zo.append(zo[-1]+c[i])
            oz.append(oz[-1])
        else:
            oz.append(oz[-1]+c[i])
            zo.append(zo[-1])

mn = float('inf')
for i in range(1, n):
    mn = min(mn, oz[i] + zo[-1]-zo[i], zo[i] + oz[-1]-oz[i])

print(mn)
