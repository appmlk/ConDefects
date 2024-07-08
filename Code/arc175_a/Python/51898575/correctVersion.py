n = int(input())
oder = list(map(lambda x: int(x)-1, input().split()))
lr = input()

def calc(d):
    ret = 1
    taken = [0] * n
    taken[oder[0]] = 1
    for i in range(1, n):
        if taken[((oder[i] + (1 if d=='L' else (-1))) + n) % n] == 1:
            if lr[oder[i]] == "?":
                ret *= 2
                ret %= 998244353
        else:
            if lr[oder[i]] != d and lr[oder[i]] != "?":
                return 0
        taken[oder[i]] = 1
    return ret

ans = 0
if lr[oder[0]] == "L":
    ans += calc('L')
elif lr[oder[0]] == "R":
    ans += calc("R")
else:
    ans += calc("L")
    ans += calc("R")

print(ans % 998244353)
