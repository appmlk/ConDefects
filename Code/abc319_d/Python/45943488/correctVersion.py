n, m = map(int, input().split())
word_lengths = list(map(int, input().split()))

total_chars = sum(word_lengths) + (n - 1)

left, right = max(word_lengths), total_chars

while left < right:
    mid = (left + right) // 2
    lines_needed, current_line_length = 1, 0

    for length in word_lengths:
        if current_line_length + length <= mid:
            current_line_length += length + 1 
        else:
            lines_needed += 1
            current_line_length = length + 1

    if lines_needed <= m:
        right = mid
    else:
        left = mid + 1

print(left)
