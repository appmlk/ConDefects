from math import log10

n,m = map(int, input().split())

mketa = int(log10(m))+1

if m % 2 == 0:
    numli = [8]
elif m % 5 == 0:
    numli = [5]
else:
    numli = [9,7]

xli = []

for num in numli:
    # resunit = num % m

    keta = mketa
    tempx = int(str(num) * keta)
    res = tempx % m

    resunit = (num * pow(10, keta-1)) % m

    while keta <= n:
        if res != 0:
            keta += 1
            
            resunit = (resunit * 10) % m
            res += resunit
            res %= m
        else:
            keta = n // keta * keta
            xli.append(int(str(num) * keta))
            keta += 1

            resunit = (num * pow(10, keta)) % m            
            res += resunit

if xli:
    ans = max(xli)
    if ans < m:
        print(-1)
    else:
        print(ans)
else:
    print(-1)