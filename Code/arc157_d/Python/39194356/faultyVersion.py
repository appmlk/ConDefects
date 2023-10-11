from itertools import groupby

MOD = 998244353
INF = MOD

H, W = map(int, input().split())
Ss = [input() for _ in range(H)]

if not any("Y" in S for S in Ss):
    print(pow(2, H - 1, MOD) * pow(2, W - 1, MOD) % MOD)
    exit()

lef_trim = 0
while "Y" not in Ss[lef_trim]:
    lef_trim += 1
rig_trim = H
while "Y" not in Ss[rig_trim - 1]:
    rig_trim -= 1

Ss = Ss[lef_trim:rig_trim]
H = rig_trim - lef_trim

csum = [[0] * (W + 1) for _ in range(H + 1)]
for i in range(H):
    for j in range(W):
        csum[i + 1][j + 1] = int(Ss[i][j] == "Y")
for i in range(H + 1):
    for j in range(W):
        csum[i][j + 1] += csum[i][j]
for i in range(H):
    for j in range(W + 1):
        csum[i + 1][j] += csum[i][j]


def get(i_begin, i_end, j_begin, j_end):
    return (
        csum[i_end][j_end]
        - csum[i_end][j_begin]
        - csum[i_begin][j_end]
        + csum[i_begin][j_begin]
    )


# print(Ss)

row_counts = [S.count("Y") for S in Ss]
all_count = sum(row_counts)
first_row_count = 0
cut_counts = set()
for first_row_length in range(H):
    if row_counts[first_row_length] > 0:
        first_row_count += row_counts[first_row_length]
        if first_row_count % 2 == 0 and all_count % first_row_count == 0:
            cut_counts.add(first_row_count)

# print(cut_counts)
answer = 0
for row_count in cut_counts:
    fail = False
    edge_points = [0]
    row_csum = 0
    factor = 1
    length = 0
    for i in range(H):
        row_csum += row_counts[i]
        if length > 0:
            if row_csum:
                factor *= length
                factor %= MOD
                length = 0
            else:
                length += 1
        if row_csum > row_count:
            fail = True
            break
        elif row_csum == row_count:
            edge_points.append(i + 1)
            row_csum = 0
            length = 1
    if row_csum or fail:
        continue

    col_cut_count = row_count // 2 - 1
    lbs_among_row = [-INF] * col_cut_count
    ubs_among_row = [INF] * col_cut_count
    for i in range(len(edge_points) - 1):
        lbs = []
        ubs = []
        i_begin = edge_points[i]
        i_end = edge_points[i + 1]
        count = 0
        for j in range(W):
            count += get(i_begin, i_end, j, j + 1)
            if count and len(lbs) > len(ubs):
                ubs.append(j)
            if count > 2:
                fail = True
                break
            elif count == 2:
                count = 0
                lbs.append(j + 1)
        if fail:
            break
        lbs.pop()
        # print(lbs, ubs, col_cut_count)
        for i in range(col_cut_count):
            lbs_among_row[i] = max(lbs_among_row[i], lbs[i])
            ubs_among_row[i] = min(ubs_among_row[i], ubs[i])
    if fail:
        continue
    col_cut_factor = 1
    for i in range(col_cut_count):
        col_cut_factor *= max(0, ubs_among_row[i] - lbs_among_row[i] + 1)
    answer += factor * col_cut_factor % MOD
    answer %= MOD
    # print(row_count, edge_points, factor, col_cut_factor)
    # print(lbs_among_row, ubs_among_row)

print(answer)
