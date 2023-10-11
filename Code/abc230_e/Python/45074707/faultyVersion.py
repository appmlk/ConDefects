n = int(input())

ans = 0

for i in range(1, int(n**0.5)+1):
    ans += n//i

ans = ans*2 - n
print(ans)
