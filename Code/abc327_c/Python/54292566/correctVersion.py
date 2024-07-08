n, n2 = 3, 9
grid = [list(map(int, input().split())) for _ in range(n2)]

rows = [[False] * n2 for _ in range(n2)]
cols = [[False] * n2 for _ in range(n2)]
blocks = [[[False] * n2 for _ in range(n)] for _ in range(n)]
# print(blocks)

valid = True
for i, line in enumerate(grid):
    for j, num in enumerate(line):
        if not rows[i][num - 1] and not cols[j][num - 1] and not blocks[i // n][j // n][num - 1]:
              rows[i][num - 1] = True
              cols[j][num - 1] = True
              blocks[i // n][j // n][num - 1] = True
        else:
            valid = False
            break

print('Yes' if valid else 'No')
