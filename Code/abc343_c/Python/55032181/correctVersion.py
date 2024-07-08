N = int(input())
x_max = round(pow(N+1, 1/3))
if pow(x_max, 3) > N:
    x_max -= 1
for x in range(x_max, 0, -1):
    x_str = str(pow(x,3))
    x_half_n = len(x_str)//2
    if x_str[:x_half_n] == x_str[::-1][:x_half_n]:
        print(pow(x,3))
        break