n = int(input())
a_s = list(map(int, input().split()))

num = 0

passengers = []
current_passenger = 0
for i in range(len(a_s)):
    current_passenger = current_passenger + a_s[i]
    passengers.append(current_passenger)

if min(passengers) >= 0:
    print(passengers[-1])
else:
    print(passengers[-1] + abs(min(passengers)))
