n_strings = int(input())
data = []
for i in range(n_strings):
    info = input().split()
    data.append((info[0], int(info[1])))

max_strings = set()
max_score = 0
max_index = 0

for i in range(n_strings):
    string, score = data[i]
    if score > max_score and string not in max_strings:
        max_score = score
        max_strings.add(string)
        max_index = i

print(max_index + 1)