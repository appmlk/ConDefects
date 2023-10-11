def f(s):
    n = len(s)
    ans = []
    l = 0
    while l < n:
        r = l + 1
        while r < n and s[l] == s[r]:
            r += 1
        ans.append((s[l], r - l))
        l = r
    return ans

s = input()
t = input()

s_l = f(s)
t_l = f(t)

if len(s_l) != len(t_l):
    exit(print('No'))

flg = 1
for i in range(len(s_l)):
    if s_l[i][0] != t_l[i][0]:
        flg = 0
    if s_l[i][1] == 1 and t_l[i][1] > 1:
        flg = 0

print('Yes' if flg else 'No')