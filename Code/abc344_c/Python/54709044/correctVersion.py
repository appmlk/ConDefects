n = int(input())
a_s = list(map(int, input().split()))
m = int(input())
b_s = list(map(int, input().split()))
l = int(input())
c_n = list(map(int, input().split()))
q = int(input())
x_s = list(map(int, input().split()))


comb_sum = []
for a in a_s:
    for b in b_s:
        for c in c_n:
            comb_sum.append(a + b + c)

sorted_comb_sum = sorted(comb_sum)

for x in x_s:
    flag = False
    left, right = 0, len(sorted_comb_sum) - 1
    while left <= right:
        mid = (left + right) // 2
        if sorted_comb_sum[mid] == x:
            print("Yes")
            flag = True
            break
        if sorted_comb_sum[mid] < x:
            left = mid + 1
        else:
            right = mid - 1
    if not flag:
        print("No")
