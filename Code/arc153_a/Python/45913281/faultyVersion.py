n = int(input())
n-=1

"""
九桁で、
1 2
5 6
7 9 が同じ数字でないといけない。
"""

#    1,2 5,6 7,9
#3 4 8は何でもいい
l = 100000
while n != 0:
    l += 1
    l %= 1000000
    n -= 1
print(l)
ans = ["" for i in range(9)]
l_s = list(str(l))
ans[0] = l_s[0]
ans[1] = l_s[0]
ans[4] = l_s[3]
ans[5] = l_s[3]
ans[6] = l_s[4]
ans[8] = l_s[4]
ans[2] = l_s[1]
ans[3] = l_s[2]
ans[7] = l_s[5]
print("".join(ans))
