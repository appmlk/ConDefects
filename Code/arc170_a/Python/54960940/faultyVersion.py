n = int(input())
s = input()
t = input()

a = []
for i in range(n):
    if s[i] != t[i]:
        if t[i] == "A":
            a.append(0)
        else:
            a.append(1)

ans = 0
left = 0
right = 0
need = 0
for i in a:
    if i == 0:
        left += 1
    else:
        right += 1
        if left < right:
            need += 1
            right -= 1
        else:
            ans += 1
            left -= 1
            right -= 1

# print(a)
before_A = False
last_B = False
for i in range(n):
    if s[i] == "A" and t[i] == "B" and before_A is False:
        print(-1)
        exit()
    if s[i] == "A":
        before_A = True
    if s[i] == "B" and t[i] == "A":
        last_B = True
    if t[i] == "B":
        last_B = False

if last_B:
    print(-1)
    exit()

print(ans + left + right + need)
