n = int(input())
q_s = list(map(int, input().split()))
a_s = list(map(int, input().split()))
b_s = list(map(int, input().split()))


max_num = 0
flag = False
for i in range(10**6):
    number_of_a = i
    for j in range(n):
        if b_s[j] == 0:
            continue
        number_of_b = (q_s[j] - a_s[j] * number_of_a) // b_s[j]
        for k in range(n):
            if a_s[k] * number_of_a + b_s[k] * number_of_b > q_s[k]:
                number_of_b = -100000000000000
                break
            if k == n - 1:
                flag = True
        if flag:
            if max_num < number_of_a + number_of_b:
                max_num = number_of_a + number_of_b
print(max_num)
