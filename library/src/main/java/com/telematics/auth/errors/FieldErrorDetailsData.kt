package com.telematics.auth.errors


/**
 * Detailed message about API-exception.
 *
 * Contains two parameters inside: field and detailed message.
 *
 * @property field On which field occurs an error
 */
class FieldErrorDetailsData(
        var field: String? = null
) : ErrorDetailsData()